import {Component, OnInit} from '@angular/core';
import CustomStore from 'devextreme/data/custom_store';
import {RoomService} from '../../services/room.service';

@Component({
  selector: 'app-room-list-page',
  templateUrl: './room-list-page.component.html',
  styleUrls: ['./room-list-page.component.scss']
})
export class RoomListPageComponent implements OnInit {
    public dataSource: any;

    constructor(private service: RoomService) {
    }

    ngOnInit(): void {
        this.dataSource = new CustomStore({
            key: 'id',
            load: () => this.service.get(),
            insert: (values) => this.service.insert(values),
            update: (key, values) => this.service.update(key, values),
            remove: (key) => this.service.delete(key)
        });
    }

    onRowUpdating(options: any): void {
        options.newData = Object.assign(options.oldData, options.newData);
    }
}
