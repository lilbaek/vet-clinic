export const navigation = [
  {
    text: 'Home',
    path: '/home',
    icon: 'home'
  },
  {
    text: 'Management',
    icon: 'folder',
    items: [
      {
        text: 'Clients',
        path: '/management/clients'
      },
      {
        text: 'Doctors',
        path: '/management/doctors'
      },
      {
        text: 'Rooms',
        path: '/management/rooms'
      }
    ]
  }
];
