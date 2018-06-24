export interface ITweet {
    id?: number;
    title?: string;
    body?: any;
    author?: string;
}

export class Tweet implements ITweet {
    constructor(public id?: number, public title?: string, public body?: any, public author?: string) {}
}
