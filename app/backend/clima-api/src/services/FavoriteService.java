package services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import model.entities.Favorite;
import model.requests.DeleteFavoriteRequest;
import model.requests.FavoriteRequest;
import model.requests.GetFavoritesRequest;
import model.requests.UpdateFavoriteRequest;
import repositories.IFavoriteRepository;

public class FavoriteService extends BaseService implements IFavoriteService {

    private static final int MAX_FAVORITES = 3;

    private final IFavoriteRepository repository;
    private final IUserService userService;

    public FavoriteService(IFavoriteRepository repository, IUserService userService) {
        this.repository = repository;
        this.userService = userService;
    }


    @Override
    public List<Favorite> getByUser(GetFavoritesRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        return repository.getByUserId(user.getId());
    }


    @Override
    public Favorite save(FavoriteRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        // Regra: máximo de 3 favoritos
        int count = repository.countByUserId(user.getId());
        if (count >= MAX_FAVORITES)
            throw new Exception("Limite de " + MAX_FAVORITES + " favoritos atingido.");

        Favorite favorite = new Favorite(
            user.getId(),
            request.title,
            request.cityName,
            request.state,
            request.country,
            request.latitude,
            request.longitude
        );

        repository.save(favorite);
        return favorite;
    }

    @Override
    public Favorite update(UpdateFavoriteRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        Favorite favorite = repository.getById(request.id);
        if (favorite == null)
            throw new Exception("Favorito não encontrado.");

        // Valida ownership
        if (!favorite.getUserId().equals(user.getId()))
            throw new Exception("Acesso negado: este favorito não pertence ao usuário autenticado.");

        if (request.title != null && !request.title.isBlank())
            favorite.setTitle(request.title);

        if (request.cityName != null && !request.cityName.isBlank())
            favorite.setCityName(request.cityName);

        if (request.state != null)
            favorite.setState(request.state);

        if (request.country != null && !request.country.isBlank())
            favorite.setCountry(request.country);

        if (request.latitude != 0)
            favorite.setLatitude(request.latitude);

        if (request.longitude != 0)
            favorite.setLongitude(request.longitude);

        repository.update(favorite);
        return favorite;
    }

    @Override
    public void delete(DeleteFavoriteRequest request) throws MalformedURLException, IOException, Exception {
        enforceRequestLimit();

        var authResult = userService.authenticate(null, request.headers, true);
        var user = authResult.user;

        Favorite favorite = repository.getById(request.id);
        if (favorite == null)
            throw new Exception("Favorito não encontrado.");

        if (!favorite.getUserId().equals(user.getId()))
            throw new Exception("Acesso negado: este favorito não pertence ao usuário autenticado.");

        repository.delete(request.id);
    }
}
