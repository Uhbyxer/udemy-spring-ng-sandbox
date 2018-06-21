import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
//NgBootstrap module!
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { TasksComponent } from './tasks/tasks.component';
import { TasksAddComponent } from './tasks/tasks-add/tasks-add.component';
import { TasksListComponent } from './tasks/tasks-list/tasks-list.component';

// import { HttpModule} from "@angular/http";
import {TaskService} from "./tasks/task.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    TasksComponent,
    TasksAddComponent,
    TasksListComponent
  ],
  imports: [
    NgbModule.forRoot(),
    BrowserModule,
    HttpClientModule,
    FormsModule

  ],
  providers: [TaskService],
  bootstrap: [AppComponent]
})
export class AppModule { }
