import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ITweet } from 'app/shared/model/tweet.model';

type EntityResponseType = HttpResponse<ITweet>;
type EntityArrayResponseType = HttpResponse<ITweet[]>;

@Injectable({ providedIn: 'root' })
export class TweetService {
    private resourceUrl = SERVER_API_URL + 'api/tweets';

    constructor(private http: HttpClient) {}

    create(tweet: ITweet): Observable<EntityResponseType> {
        return this.http.post<ITweet>(this.resourceUrl, tweet, { observe: 'response' });
    }

    update(tweet: ITweet): Observable<EntityResponseType> {
        return this.http.put<ITweet>(this.resourceUrl, tweet, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<ITweet>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ITweet[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
