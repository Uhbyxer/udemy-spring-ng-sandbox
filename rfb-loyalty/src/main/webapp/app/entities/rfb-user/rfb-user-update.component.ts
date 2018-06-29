import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IRfbUser } from 'app/shared/model/rfb-user.model';
import { RfbUserService } from './rfb-user.service';

@Component({
    selector: 'jhi-rfb-user-update',
    templateUrl: './rfb-user-update.component.html'
})
export class RfbUserUpdateComponent implements OnInit {
    private _rfbUser: IRfbUser;
    isSaving: boolean;

    constructor(private rfbUserService: RfbUserService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ rfbUser }) => {
            this.rfbUser = rfbUser;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.rfbUser.id !== undefined) {
            this.subscribeToSaveResponse(this.rfbUserService.update(this.rfbUser));
        } else {
            this.subscribeToSaveResponse(this.rfbUserService.create(this.rfbUser));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IRfbUser>>) {
        result.subscribe((res: HttpResponse<IRfbUser>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get rfbUser() {
        return this._rfbUser;
    }

    set rfbUser(rfbUser: IRfbUser) {
        this._rfbUser = rfbUser;
    }
}
