#!/bin/bash

set -e
set -u

echo "*********Creating user and databases"
psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" <<-EOSQL

	    create user auth_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'auth_pw';
	    create user account_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'account_pw';
	    create user company_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'company_pw';
	    create user cargo_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'cargo_pw';
	    create user transport_exchange_app with login nosuperuser nocreatedb nocreaterole inherit noreplication  password 'transport_exchange_pw';

		  CREATE DATABASE account;
		  CREATE DATABASE auth;
		  CREATE DATABASE company;
		  CREATE DATABASE cargo;
		  CREATE DATABASE transport_exchange;

		  \c account
      alter default privileges for role "account_app" in schema public grant select, insert, update, delete on tables to "account_app";
      alter default privileges for role "account_app" in schema public grant select, usage on sequences to "account_app";

      \c auth
      alter default privileges for role "auth_app" in schema public grant select, insert, update, delete on tables to "auth_app";
      alter default privileges for role "auth_app" in schema public grant select, usage on sequences to "auth_app";

      \c company
      alter default privileges for role "company_app" in schema public grant select, insert, update, delete on tables to "company_app";
      alter default privileges for role "company_app" in schema public grant select, usage on sequences to "company_app";

      \c cargo
      alter default privileges for role "cargo_app" in schema public grant select, insert, update, delete on tables to "cargo_app";
      alter default privileges for role "cargo_app" in schema public grant select, usage on sequences to "cargo_app";

      \c transport_exchange
      alter default privileges for role "transport_exchange_app" in schema public grant select, insert, update, delete on tables to "transport_exchange_app";
      alter default privileges for role "transport_exchange_app" in schema public grant select, usage on sequences to "transport_exchange_app";

EOSQL
echo "*********Completed user and databases"
