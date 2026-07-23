import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { AsyncPipe, NgIf } from '@angular/common';
import { Header } from './components/header/header';
import { LoadingService } from './services/loading.service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, AsyncPipe, NgIf, Header],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  private loadingService = inject(LoadingService);
  // Expose loading state stream for the global spinner
  isLoading$ = this.loadingService.isLoading$;
}

