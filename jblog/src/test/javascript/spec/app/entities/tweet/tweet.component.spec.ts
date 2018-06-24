/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { Observable, of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { JblogTestModule } from '../../../test.module';
import { TweetComponent } from 'app/entities/tweet/tweet.component';
import { TweetService } from 'app/entities/tweet/tweet.service';
import { Tweet } from 'app/shared/model/tweet.model';

describe('Component Tests', () => {
    describe('Tweet Management Component', () => {
        let comp: TweetComponent;
        let fixture: ComponentFixture<TweetComponent>;
        let service: TweetService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JblogTestModule],
                declarations: [TweetComponent],
                providers: []
            })
                .overrideTemplate(TweetComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(TweetComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TweetService);
        });

        it('Should call load all on init', () => {
            // GIVEN
            const headers = new HttpHeaders().append('link', 'link;link');
            spyOn(service, 'query').and.returnValue(
                of(
                    new HttpResponse({
                        body: [new Tweet(123)],
                        headers
                    })
                )
            );

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.query).toHaveBeenCalled();
            expect(comp.tweets[0]).toEqual(jasmine.objectContaining({ id: 123 }));
        });
    });
});
