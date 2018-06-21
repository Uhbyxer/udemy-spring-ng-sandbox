import {Http} from "@angular/http";
import { map } from 'rxjs/operators';
import {Injectable} from "@angular/core";

@Injectable()
export class TaskService {

  constructor(private http: Http) {

  }

  getTasks() {
    return this.http.get('/api/tasks').pipe(map(response => response.json()));
  }
}
