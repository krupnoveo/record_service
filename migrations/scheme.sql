create table if not exists available_times (
    id uuid primary key default gen_random_uuid(),
    time timestamp with time zone not null,
    is_available boolean not null
);

create table if not exists records (
    id uuid primary key default gen_random_uuid(),
    client_id uuid not null,
    barber_id uuid not null,
    barbershop_id uuid not null,
    service_id uuid not null,
    time_id uuid references available_times(id)
);
