import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { IndexNavigationComponent } from './index-navigation.component';

describe('IndexNavigationComponent', () => {
  let component: IndexNavigationComponent;
  let fixture: ComponentFixture<IndexNavigationComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ IndexNavigationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(IndexNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
