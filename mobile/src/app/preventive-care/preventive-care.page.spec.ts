import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PreventiveCarePage } from './preventive-care.page';

describe('PreventiveCarePage', () => {
  let component: PreventiveCarePage;
  let fixture: ComponentFixture<PreventiveCarePage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PreventiveCarePage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
