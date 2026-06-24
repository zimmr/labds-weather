package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.NoSuchElementException;

import javax.naming.AuthenticationException;

import model.dtos.AuthenticationResultDto;
import model.entities.User;
import model.requests.GetUserRequest;
import model.requests.RequestHeaders;
import model.requests.UserLoginRequest;
import model.requests.UserRequest;
import model.responses.UserResponse;
import repositories.IUserRepository;

public class UserService extends BaseService implements IUserService {

    private IUserRepository repository;

    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    public User get(GetUserRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();
        var user = repository.get(request.id);

        // Exemplos de autenticação 

        // Cenário 1:
        // Caso a autenticação seja obrigatória para realizar a ação, como na atualização de dados do usuário
        authenticate(user, request.headers, true);

        // Cenário 2:
        // Caso a autenticação não seja obrigatória para realizar a ação, como na consulta de tempo.
        // Se tiver qualquer valor nos headers, vai tentar autenticar e estourar exceção se falhar.
        // Se ambos os campos dos headers estiverem vazios, não tenta autenticar.
        // var authenticationResult = authenticate(null, request.headers, false);
        // if (authenticationResult.isAuthenticated)
        // {
        //     var authenticatedUser = authenticationResult.user;
        //     // Salvar histórico de consulta, etc
        //     System.out.println("Autenticado: " + authenticatedUser.getName());
        // }

        if (user == null)
            throw new Exception("Usuário não encontrado.");

        return user;
    }

    public User save(UserRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();
        User user = new User(request.name, request.email, request.password, request.celsius);
        
        if (repository.exists(user.getEmail()))
            throw new Exception("Já existe um usuário cadastrado com este email.");

        repository.save(user);

        return user;
    }

    public UserResponse login(UserLoginRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        User user = repository.getByEmail(request.email);

        if (user == null)
            throw new Exception("Usuário '" + request.email + "' não encontrado.");
        
        var isAuthenticated = user.authenticate(request.email, request.password);

        if (!isAuthenticated)
            throw new Exception();
            
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getUseCelsius());
    }

    /**
     * Autentica um usuário com base nos headers informados.
     * @param user {@link User} já carregado. Pode ser null.
     * @param headers {@link RequestHeaders} contendo login e senha.
     * @param isObligatory Define se a autenticação é obrigatória. Se for obrigatória, estoura exceção caso headers estejam vazios.
     * @return {@link AuthenticationResultDto} com usuário e resultado da autenticação.
     * @throws IllegalArgumentException Se isObligatory = true e headers estiverem vazios.
     * @throws NoSuchElementException Se tentar buscar o usuário no banco de dados e não o encontrar.
     * @throws AuthenticationException Se ocorrer falha na autenticação.
     */
    public AuthenticationResultDto authenticate(User user, RequestHeaders headers, boolean isObligatory) throws IllegalArgumentException, NoSuchElementException, AuthenticationException {

        // Tratamento de headers
        var login = headers.getLogin();
        var password = headers.getPassword();

        var isValidLogin = login != null && !login.isBlank();
        var isValidPassword = password != null && !password.isBlank();
        
        if (isObligatory && !isValidLogin)
            throw new IllegalArgumentException("Falha de autenticação: Header 'login' não informado.");

        if ((isObligatory || isValidLogin) && !isValidPassword)
            throw new IllegalArgumentException("Falha de autenticação: Header 'password' não informado.");

        if (!isValidLogin || !isValidPassword)
            return new AuthenticationResultDto(user);
        
        // Tratamento de usuário
        user = user == null ? repository.getByEmail(login) : user;
        if (user == null)
            throw new NoSuchElementException("Usuário '" + login + "' não encontrado.");

        // Autenticação
        var isAuthenticated = user.authenticate(headers.getLogin(), headers.getPassword());
        if (!isAuthenticated)
            throw new AuthenticationException("Falha de autenticação: Login ou senha inválidos.");
        
        return new AuthenticationResultDto(user, isAuthenticated);
    }
}