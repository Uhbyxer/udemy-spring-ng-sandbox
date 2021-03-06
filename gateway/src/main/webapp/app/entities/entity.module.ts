import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { GatewaySpeakerModule as ConferenceSpeakerModule } from './conference/speaker/speaker.module';
import { GatewaySessionModule as ConferenceSessionModule } from './conference/session/session.module';
import { GatewayBlogModule as BlogBlogModule } from './blog/blog/blog.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ConferenceSpeakerModule,
        ConferenceSessionModule,
        BlogBlogModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class GatewayEntityModule {}
