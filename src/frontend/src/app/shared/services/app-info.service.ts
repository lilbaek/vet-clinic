import { Injectable } from '@angular/core';

@Injectable()
export class AppInfoService {
  constructor() {}

  public get title() {
    return 'Vet clinic';
  }

  public get currentYear() {
    return new Date().getFullYear();
  }
}
