/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { JblogTestModule } from '../../../test.module';
import { TweetDeleteDialogComponent } from 'app/entities/tweet/tweet-delete-dialog.component';
import { TweetService } from 'app/entities/tweet/tweet.service';

describe('Component Tests', () => {
    describe('Tweet Management Delete Component', () => {
        let comp: TweetDeleteDialogComponent;
        let fixture: ComponentFixture<TweetDeleteDialogComponent>;
        let service: TweetService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JblogTestModule],
                declarations: [TweetDeleteDialogComponent]
            })
                .overrideTemplate(TweetDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TweetDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TweetService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it(
                'Should call delete service on confirmDelete',
                inject(
                    [],
                    fakeAsync(() => {
                        // GIVEN
                        spyOn(service, 'delete').and.returnValue(of({}));

                        // WHEN
                        comp.confirmDelete(123);
                        tick();

                        // THEN
                        expect(service.delete).toHaveBeenCalledWith(123);
                        expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                        expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                    })
                )
            );
        });
    });
});
