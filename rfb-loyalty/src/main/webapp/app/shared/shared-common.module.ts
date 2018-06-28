import { NgModule } from '@angular/core';

import { RfblotaltySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [RfblotaltySharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [RfblotaltySharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class RfblotaltySharedCommonModule {}
