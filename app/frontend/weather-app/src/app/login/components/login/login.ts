import { Component } from '@angular/core';
import { RouterLink, Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-login',
  imports: [RouterLink, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  email = '';
  password = '';
  erro = '';
  carregando = false;

  constructor(
    private userService: UserService,
    private router: Router
  ) {}

  login() {
    this.erro = '';
    this.carregando = true;

    this.userService.login(this.email, this.password).subscribe({
      next: (res) => {
        console.log(res);

        localStorage.setItem('login', this.email);
        localStorage.setItem('password', this.password);

        this.router.navigate(['/']);
      },
      error: (err) => {
        this.erro = err.status === 401
          ? 'Email ou senha incorretos.'
          : 'Erro ao conectar com o servidor.';
        this.carregando = false;
      },
    });
  }
}