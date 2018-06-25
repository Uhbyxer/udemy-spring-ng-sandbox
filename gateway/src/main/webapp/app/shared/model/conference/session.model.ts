import { Moment } from 'moment';
import { ISpeaker } from 'app/shared/model/conference/speaker.model';

export interface ISession {
    id?: number;
    title?: string;
    decriptionContentType?: string;
    decription?: any;
    startDateTime?: Moment;
    endDateTime?: Moment;
    speakers?: ISpeaker[];
}

export class Session implements ISession {
    constructor(
        public id?: number,
        public title?: string,
        public decriptionContentType?: string,
        public decription?: any,
        public startDateTime?: Moment,
        public endDateTime?: Moment,
        public speakers?: ISpeaker[]
    ) {}
}
