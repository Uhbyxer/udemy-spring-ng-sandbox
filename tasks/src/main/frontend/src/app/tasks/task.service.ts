// import {Http} from "@angular/http";
// import { map } from 'rxjs/operators';
import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class TaskService {

  constructor(private http: HttpClient) {

  }

  getTasks() {
    // return this.http.get('/api/tasks').pipe(map(response => response.json()));
    // return this.http.get('/api/tasks').pipe();
    return this.http.get('/api/tasks');
  }
}
