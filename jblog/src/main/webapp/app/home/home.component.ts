import { Component, OnInit } from '@angular/core';
import { PostService } from 'app/entities/post';
import { IPost, Post } from 'app/shared/model/post.model';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.css']
})
export class HomeComponent implements OnInit {
    posts: Post[] = [];

    constructor(private postService: PostService) {}

    loadAll() {
        this.postService.query().subscribe(
            (res: HttpResponse<IPost[]>) => {
                this.posts = res.body;
            },
            (res: HttpErrorResponse) => console.log('Some error: ' + res)
        );
    }

    ngOnInit(): void {
        console.log('Posts on init......');
        this.loadAll();
    }
}
