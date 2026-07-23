import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: '[appHighlight]',
  standalone: true
})
export class Highlight {
  @Input('appHighlight') highlightColor: string = 'yellow';

  constructor(private el: ElementRef) {}

  @HostListener('mouseenter') onMouseEnter() {
    this.highlight(this.highlightColor);
  }

  @HostListener('mouseleave') onMouseLeave() {
    this.highlight('');
  }

  private highlight(color: string) {
    this.el.nativeElement.style.backgroundColor = color;
    // Add a transition effect for smooth hover
    if (color) {
      this.el.nativeElement.style.transition = 'background-color 0.25s ease';
    }
  }
}
