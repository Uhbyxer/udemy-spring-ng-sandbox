import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JblogSharedModule } from 'app/shared';
import {
    TweetComponent,
    TweetDetailComponent,
    TweetUpdateComponent,
    TweetDeletePopupComponent,
    TweetDeleteDialogComponent,
    tweetRoute,
    tweetPopupRoute
} from './';

const ENTITY_STATES = [...tweetRoute, ...tweetPopupRoute];

@NgModule({
    imports: [JblogSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [TweetComponent, TweetDetailComponent, TweetUpdateComponent, TweetDeleteDialogComponent, TweetDeletePopupComponent],
    entryComponents: [TweetComponent, TweetUpdateComponent, TweetDeleteDialogComponent, TweetDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class JblogTweetModule {}
