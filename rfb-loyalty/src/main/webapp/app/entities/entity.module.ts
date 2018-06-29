import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { RfblotaltyRfbLocationModule } from './rfb-location/rfb-location.module';
import { RfblotaltyRfbEventModule } from './rfb-event/rfb-event.module';
import { RfblotaltyRfbEventAttendanceModule } from './rfb-event-attendance/rfb-event-attendance.module';
import { RfblotaltyRfbUserModule } from './rfb-user/rfb-user.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        RfblotaltyRfbLocationModule,
        RfblotaltyRfbEventModule,
        RfblotaltyRfbEventAttendanceModule,
        RfblotaltyRfbUserModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class RfblotaltyEntityModule {}
