import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NakedComponent } from './naked.component';

describe('NakedComponent', () => {
  let component: NakedComponent;
  let fixture: ComponentFixture<NakedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NakedComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NakedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
