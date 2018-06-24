/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { JblogTestModule } from '../../../test.module';
import { TweetUpdateComponent } from 'app/entities/tweet/tweet-update.component';
import { TweetService } from 'app/entities/tweet/tweet.service';
import { Tweet } from 'app/shared/model/tweet.model';

describe('Component Tests', () => {
    describe('Tweet Management Update Component', () => {
        let comp: TweetUpdateComponent;
        let fixture: ComponentFixture<TweetUpdateComponent>;
        let service: TweetService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JblogTestModule],
                declarations: [TweetUpdateComponent]
            })
                .overrideTemplate(TweetUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TweetUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TweetService);
        });

        describe('save', () => {
            it(
                'Should call update service on save for existing entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tweet(123);
                    spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tweet = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.update).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );

            it(
                'Should call create service on save for new entity',
                fakeAsync(() => {
                    // GIVEN
                    const entity = new Tweet();
                    spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                    comp.tweet = entity;
                    // WHEN
                    comp.save();
                    tick(); // simulate async

                    // THEN
                    expect(service.create).toHaveBeenCalledWith(entity);
                    expect(comp.isSaving).toEqual(false);
                })
            );
        });
    });
});
