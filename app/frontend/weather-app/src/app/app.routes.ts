import { Routes } from '@angular/router';
import { Login } from './login/components/login/login';
import { Cadastro } from './cadastro/components/cadastro/cadastro';

export const routes: Routes = [
    {
        path: '',
        component: Login
    },
    {
        path: 'register',
        component: Cadastro
    },
];
