import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { ITweet } from 'app/shared/model/tweet.model';
import { TweetService } from './tweet.service';

@Component({
    selector: 'jhi-tweet-update',
    templateUrl: './tweet-update.component.html'
})
export class TweetUpdateComponent implements OnInit {
    private _tweet: ITweet;
    isSaving: boolean;

    constructor(private dataUtils: JhiDataUtils, private tweetService: TweetService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
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

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.tweet.id !== undefined) {
            this.subscribeToSaveResponse(this.tweetService.update(this.tweet));
        } else {
            this.subscribeToSaveResponse(this.tweetService.create(this.tweet));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ITweet>>) {
        result.subscribe((res: HttpResponse<ITweet>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get tweet() {
        return this._tweet;
    }

    set tweet(tweet: ITweet) {
        this._tweet = tweet;
    }
}
