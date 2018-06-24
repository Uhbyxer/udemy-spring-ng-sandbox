import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService, JhiDataUtils } from 'ng-jhipster';

import { ITweet } from 'app/shared/model/tweet.model';
import { Principal } from 'app/core';
import { TweetService } from './tweet.service';

@Component({
    selector: 'jhi-tweet',
    templateUrl: './tweet.component.html'
})
export class TweetComponent implements OnInit, OnDestroy {
    tweets: ITweet[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private tweetService: TweetService,
        private jhiAlertService: JhiAlertService,
        private dataUtils: JhiDataUtils,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.tweetService.query().subscribe(
            (res: HttpResponse<ITweet[]>) => {
                this.tweets = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInTweets();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: ITweet) {
        return item.id;
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    registerChangeInTweets() {
        this.eventSubscriber = this.eventManager.subscribe('tweetListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
