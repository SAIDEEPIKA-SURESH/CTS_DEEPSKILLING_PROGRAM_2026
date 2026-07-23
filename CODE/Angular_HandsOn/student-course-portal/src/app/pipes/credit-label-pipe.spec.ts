import { CreditLabelPipe } from './credit-label-pipe';

describe('CreditLabelPipe', () => {
  let pipe: CreditLabelPipe;

  beforeEach(() => {
    pipe = new CreditLabelPipe();
  });

  it('create an instance', () => {
    expect(pipe).toBeTruthy();
  });

  it('should transform 1 to "1 Credit"', () => {
    expect(pipe.transform(1)).toBe('1 Credit');
  });

  it('should transform 3 to "3 Credits"', () => {
    expect(pipe.transform(3)).toBe('3 Credits');
  });

  it('should transform 0, null, or undefined to "No Credits"', () => {
    expect(pipe.transform(0)).toBe('No Credits');
    expect(pipe.transform(null)).toBe('No Credits');
    expect(pipe.transform(undefined)).toBe('No Credits');
  });
});
