import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

interface CidadeClima {
  nome: string;
  pais: string;
  temperatura: number;
  descricao: string;
  icone: string;
}

@Component({
  selector: 'app-home',
  imports: [
    CommonModule,
    FormsModule,
    RouterLink
  ],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home implements OnInit { 
  termoBusca = '';
  pesquisou = false;

  cidades: CidadeClima[] = [];
  mockCidades: CidadeClima[] = [];

  ngOnInit(): void {
    // Simulação da API
    this.mockCidades = [
      {
        nome: 'São Paulo',
        pais: 'Brasil',
        temperatura: 24,
        descricao: 'Ensolarado',
        icone: '☀️'
      },
      {
        nome: 'Rio de Janeiro',
        pais: 'Brasil',
        temperatura: 28,
        descricao: 'Parcialmente nublado',
        icone: '☁️'
      },
      {
        nome: 'Curitiba',
        pais: 'Brasil',
        temperatura: 18,
        descricao: 'Chuvoso',
        icone: '🌧️'
      },
      {
        nome: 'Belo Horizonte',
        pais: 'Brasil',
        temperatura: 26,
        descricao: 'Ensolarado',
        icone: '☀️'
      },
      {
        nome: 'Porto Alegre',
        pais: 'Brasil',
        temperatura: 20,
        descricao: 'Ventoso',
        icone: '💨'
      },
      {
        nome: 'Salvador',
        pais: 'Brasil',
        temperatura: 30,
        descricao: 'Ensolarado',
        icone: '☀️'
      }
    ];
  }

 buscar(): void {

  if (!this.termoBusca.trim()) {
    return;
  }

  this.pesquisou = true;

  // Futuramente:
  // this.cidades = respostaDaApi;

  // Simulação
  this.cidades = this.mockCidades.filter(x =>
    x.nome.toLowerCase().includes(this.termoBusca.toLowerCase())
  );
}
}
