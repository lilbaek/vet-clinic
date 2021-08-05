import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ClientListPageComponent} from './components/client-list-page/client-list-page.component';
import {DoctorListPageComponent} from './components/doctor-list-page/doctor-list-page.component';
import {RoomListPageComponent} from './components/room-list-page/room-list-page.component';

const routes: Routes = [
    {
        path: 'doctors',
        component: DoctorListPageComponent
    },
    {
        path: 'rooms',
        component: RoomListPageComponent
    },
    {
        path: 'clients',
        component: ClientListPageComponent
    }
];

@NgModule({
    imports: [
        RouterModule.forChild(routes)
    ],
    exports: [
        RouterModule
    ]
})
export class ManagementRoutingModule {
}
