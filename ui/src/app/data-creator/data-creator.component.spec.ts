import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DataCreatorComponent } from './data-creator.component';

describe('DataCreatorComponent', () => {
  let component: DataCreatorComponent;
  let fixture: ComponentFixture<DataCreatorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DataCreatorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DataCreatorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
