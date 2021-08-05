import {Component, Input, OnInit} from '@angular/core';
import CustomStore from 'devextreme/data/custom_store';
import {PatientService} from '../../services/patient.service';

@Component({
  selector: 'app-client-detail-grid',
  templateUrl: './client-detail-grid.component.html',
  styleUrls: ['./client-detail-grid.component.scss']
})
export class ClientDetailGridComponent implements OnInit {
    // @ts-ignore
    @Input() key: number;
    public dataSource: any;

    constructor(private service: PatientService) {
    }

    ngOnInit(): void {
        this.dataSource = new CustomStore({
            key: 'id',
            load: () => this.service.allByClientId(this.key),
            insert: (values) => this.service.insert(values),
            update: (key, values) => this.service.update(key, values),
            remove: (key) => this.service.delete(key)
        });
    }

    onRowUpdating(options: any): void {
        options.newData = Object.assign(options.oldData, options.newData);
    }

    onRowInserting($event: any): void {
        $event.data.clientId = this.key;
    }
}
