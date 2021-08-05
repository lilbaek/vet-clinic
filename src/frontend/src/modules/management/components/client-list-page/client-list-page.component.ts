import {Component, Input, OnInit} from '@angular/core';
import CustomStore from 'devextreme/data/custom_store';
import {ClientService} from '../../services/client.service';
import {DoctorService} from '../../services/doctor.service';

@Component({
    selector: 'app-client-list-page',
    templateUrl: './client-list-page.component.html',
    styleUrls: ['./client-list-page.component.scss']
})
export class ClientListPageComponent implements OnInit {
    public dataSource: any;
    public doctorData: any;

    constructor(private service: ClientService, private doctorService: DoctorService) {
    }

    ngOnInit(): void {
        this.dataSource = new CustomStore({
            key: 'id',
            load: () => this.service.get(),
            insert: (values) => this.service.insert(values),
            update: (key, values) => this.service.update(key, values),
            remove: (key) => this.service.delete(key)
        });
        this.doctorData = {
            paginate: true,
            store: new CustomStore({
                key: 'id',
                loadMode: 'raw',
                load: () => this.doctorService.get()
            })
        };
    }

    onRowUpdating(options: any): void {
        options.newData = Object.assign(options.oldData, options.newData);
    }

}
