import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable } from 'rxjs';
import { Tweet } from 'app/shared/model/tweet.model';
import { TweetService } from './tweet.service';
import { TweetComponent } from './tweet.component';
import { TweetDetailComponent } from './tweet-detail.component';
import { TweetUpdateComponent } from './tweet-update.component';
import { TweetDeletePopupComponent } from './tweet-delete-dialog.component';
import { ITweet } from 'app/shared/model/tweet.model';

@Injectable({ providedIn: 'root' })
export class TweetResolve implements Resolve<ITweet> {
    constructor(private service: TweetService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).map((tweet: HttpResponse<Tweet>) => tweet.body);
        }
        return Observable.of(new Tweet());
    }
}

export const tweetRoute: Routes = [
    {
        path: 'tweet',
        component: TweetComponent,
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tweets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tweet/:id/view',
        component: TweetDetailComponent,
        resolve: {
            tweet: TweetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tweets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tweet/new',
        component: TweetUpdateComponent,
        resolve: {
            tweet: TweetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tweets'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'tweet/:id/edit',
        component: TweetUpdateComponent,
        resolve: {
            tweet: TweetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tweets'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const tweetPopupRoute: Routes = [
    {
        path: 'tweet/:id/delete',
        component: TweetDeletePopupComponent,
        resolve: {
            tweet: TweetResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'Tweets'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
