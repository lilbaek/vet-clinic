import {Component, OnInit} from '@angular/core';
import CustomStore from 'devextreme/data/custom_store';
import {DoctorService} from '../../services/doctor.service';

@Component({
    selector: 'app-doctor-list-page',
    templateUrl: './doctor-list-page.component.html',
    styleUrls: ['./doctor-list-page.component.scss']
})
export class DoctorListPageComponent implements OnInit {
    public dataSource: any;

    constructor(private doctorService: DoctorService) {
    }

    ngOnInit(): void {
        this.dataSource = new CustomStore({
            key: 'id',
            load: () => this.doctorService.get(),
            insert: (values) => this.doctorService.insert(values),
            update: (key, values) => this.doctorService.update(key, values),
            remove: (key) => this.doctorService.delete(key)
        });
    }

    onRowUpdating(options: any): void {
        options.newData = Object.assign(options.oldData, options.newData);
    }
}
