import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LayoutCreatorComponent } from './layout-creator.component';

describe('LayoutCreatorComponent', () => {
  let component: LayoutCreatorComponent;
  let fixture: ComponentFixture<LayoutCreatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LayoutCreatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LayoutCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
