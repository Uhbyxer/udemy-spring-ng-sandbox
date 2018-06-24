import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { ITweet } from 'app/shared/model/tweet.model';

@Component({
    selector: 'jhi-tweet-detail',
    templateUrl: './tweet-detail.component.html'
})
export class TweetDetailComponent implements OnInit {
    tweet: ITweet;

    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ tweet }) => {
            this.tweet = tweet;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
