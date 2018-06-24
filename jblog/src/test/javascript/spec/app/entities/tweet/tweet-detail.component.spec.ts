/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { JblogTestModule } from '../../../test.module';
import { TweetDetailComponent } from 'app/entities/tweet/tweet-detail.component';
import { Tweet } from 'app/shared/model/tweet.model';

describe('Component Tests', () => {
    describe('Tweet Management Detail Component', () => {
        let comp: TweetDetailComponent;
        let fixture: ComponentFixture<TweetDetailComponent>;
        const route = ({ data: of({ tweet: new Tweet(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [JblogTestModule],
                declarations: [TweetDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(TweetDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(TweetDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.tweet).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
