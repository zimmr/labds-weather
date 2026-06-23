import { Routes } from '@angular/router';
import { Login } from './login/components/login/login';
import { Cadastro } from './cadastro/components/cadastro/cadastro';
import { Home } from './home/components/home/home';

export const routes: Routes = [
    {
        path: '',
        component: Home
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'register',
        component: Cadastro
    },
];
