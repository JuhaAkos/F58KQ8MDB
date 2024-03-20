create or replace procedure aut_arnov2(szinbe in char, ert in int) is
    cursor cur_a is select * from autok where szin=szinbe
    for update of ar;
begin
    open cur_a;
    for db in cur_a
    loop
        fetch cur_a into db;
        exit when cur_a%notfound;
        update autok set ar=db.ar*(1+ert/100) where current of cur_a;
    end loop;
    close cur_a;
end;
