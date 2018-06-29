export interface IRfbUser {
    id?: number;
    username?: string;
}

export class RfbUser implements IRfbUser {
    constructor(public id?: number, public username?: string) {}
}
