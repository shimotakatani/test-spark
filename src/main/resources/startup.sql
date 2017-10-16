CREATE TABLE public.users (
	id UUID NOT NULL,
    create_time BIGINT,
    update_time BIGINT,
    status TEXT,
    fio TEXT,
    phone_number TEXT,
    email TEXT,
    cheef_id UUID,
    department_id UUID
);

insert into users(id, create_time, update_time, status, fio, phone_number, email)
	values('a0eebc99-9c0b-4ef8-bb6d-7bb9bd380a16', 3124312, 5435433, 'ACTIVE', 'Первый', '+799945546', 'dsf@dfg.ru')

CREATE TABLE public.departmentId (
	id UUID NOT NULL,
    create_time BIGINT,
    update_time BIGINT,
    status TEXT,
    name TEXT
);