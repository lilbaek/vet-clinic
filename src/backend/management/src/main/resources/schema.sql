CREATE DATABASE [Management]

USE [Management]

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

CREATE TABLE [Patients] (
    [Id] int NOT NULL IDENTITY,
    [ClientId] int NOT NULL,
    [Name] nvarchar(max) NULL,
    [Sex] nvarchar(max) NULL,
    [PreferredDoctorId] int NULL,
    CONSTRAINT [PK_Patients] PRIMARY KEY ([Id]),
    CONSTRAINT [FK_Patients_Clients_ClientId] FOREIGN KEY ([ClientId]) REFERENCES [Clients] ([Id]) ON DELETE CASCADE
    );
