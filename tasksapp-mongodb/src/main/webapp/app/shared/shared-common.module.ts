import { NgModule } from '@angular/core';

import { TasksAppMongoSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [TasksAppMongoSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [TasksAppMongoSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class TasksAppMongoSharedCommonModule {}
