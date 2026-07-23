import { Highlight } from './highlight';
import { ElementRef } from '@angular/core';

describe('Highlight', () => {
  it('should create an instance', () => {
    const mockEl = { nativeElement: document.createElement('div') } as ElementRef;
    const directive = new Highlight(mockEl);
    expect(directive).toBeTruthy();
  });
});
