import {CommonModule} from '@angular/common';
import {HttpClientModule} from '@angular/common/http';
import {NgModule} from '@angular/core';
import {DxButtonModule, DxColorBoxModule, DxDataGridModule, DxFormModule, DxRadioGroupModule, DxSwitchModule, DxTextBoxModule, DxValidationGroupModule, DxValidatorModule} from 'devextreme-angular';
import {SharedModule} from '../shared/shared.module';
import {ClientListPageComponent} from './components/client-list-page/client-list-page.component';
import {DoctorListPageComponent} from './components/doctor-list-page/doctor-list-page.component';
import {RoomListPageComponent} from './components/room-list-page/room-list-page.component';
import {ManagementRoutingModule} from './management-routing.module';
import { ClientDetailGridComponent } from './components/client-detail-grid/client-detail-grid.component';

@NgModule({
    declarations: [
        DoctorListPageComponent,
        RoomListPageComponent,
        ClientListPageComponent,
        ClientDetailGridComponent
    ],
    imports: [
        CommonModule,
        ManagementRoutingModule,
        DxButtonModule,
        DxDataGridModule,
        DxFormModule,
        DxSwitchModule,
        DxColorBoxModule,
        DxRadioGroupModule,
        DxTextBoxModule,
        DxValidatorModule,
        DxValidationGroupModule,
        HttpClientModule,
        SharedModule
    ]
})
export class ManagementModule {
}
