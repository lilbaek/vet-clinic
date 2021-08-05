CREATE DATABASE [Frontdesk]

USE [Frontdesk]

GO

CREATE TABLE [AppointmentTypes] (
    [Id] int NOT NULL IDENTITY,
    [Name] nvarchar(max) NULL,
    [Code] nvarchar(max) NULL,
    [Duration] int NOT NULL,
    CONSTRAINT [PK_AppointmentTypes] PRIMARY KEY ([Id])
    );
GO

CREATE TABLE [Clients] (
    [Id] int NOT NULL IDENTITY,
    [FullName] nvarchar(max) NULL,
    [PreferredName] nvarchar(max) NULL,
    [Salutation] nvarchar(max) NULL,
    [EmailAddress] nvarchar(max) NULL,
    [PreferredDoctorId] int NOT NULL,
    CONSTRAINT [PK_Clients] PRIMARY KEY ([Id])
    );
GO

CREATE TABLE [Doctors] (
    [Id] int NOT NULL IDENTITY,
    [Name] nvarchar(max) NULL,
    CONSTRAINT [PK_Doctors] PRIMARY KEY ([Id])
    );
GO

CREATE TABLE [Rooms] (
    [Id] int NOT NULL IDENTITY,
    [Name] nvarchar(max) NULL,
    CONSTRAINT [PK_Rooms] PRIMARY KEY ([Id])
    );
GO

CREATE TABLE [Schedules] (
    [Id] uniqueidentifier NOT NULL,
    [ClinicId] int NOT NULL,
     CONSTRAINT [PK_Schedules] PRIMARY KEY ([Id])
    );
GO

CREATE TABLE [Patients] (
    [Id] int NOT NULL IDENTITY,
    [ClientId] int NOT NULL,
    [Name] nvarchar(max) NULL,
    [Sex] nvarchar(max) NULL,
    [AnimalType_Species] nvarchar(50) NULL,
    [AnimalType_Breed] nvarchar(50) NULL,
    [PreferredDoctorId] int NULL,
    CONSTRAINT [PK_Patients] PRIMARY KEY ([Id]),
    CONSTRAINT [FK_Patients_Clients_ClientId] FOREIGN KEY ([ClientId]) REFERENCES [Clients] ([Id]) ON DELETE CASCADE
    );
GO

CREATE TABLE [Appointments] (
    [Id] uniqueidentifier NOT NULL,
    [ScheduleId] uniqueidentifier NOT NULL,
    [ClientId] int NOT NULL,
    [PatientId] int NOT NULL,
    [RoomId] int NOT NULL,
    [DoctorId] int NULL,
    [AppointmentTypeId] int NOT NULL,
    [TimeRange_Start] datetime2 NULL,
    [TimeRange_End] datetime2 NULL,
    [Title] nvarchar(max) NULL,
    [DateTimeConfirmed] datetime2 NULL,
    CONSTRAINT [PK_Appointments] PRIMARY KEY ([Id]),
    CONSTRAINT [FK_Appointments_Schedules_ScheduleId] FOREIGN KEY ([ScheduleId]) REFERENCES [Schedules] ([Id]) ON DELETE CASCADE
    );