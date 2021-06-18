drop user payroll;
drop database payroll_db;
create  user payroll with encrypted password 'Payroll123' createdb;

create database payroll_db
with owner = payroll

grant all privileges on database payroll_db to payroll;
