create or replace procedure aut_arnov3(szinbe in char, ert in int) is
    cursor cur_b is select * from autok where szin=szinbe
    for update of ar;
    b cur_b%rowtype;
    darab number := 0;
begin
    if cur_b%isopen then
        close cur_b;
    end if;
    open cur_b;
    loop
        fetch cur_b into b;
        exit when cur_b%notfound;
        update autok set ar=b.ar*(1+ert/100) where current of cur_b;
        darab:=darab+1;
    end loop;
    close cur_b;

    dbms_output.put_line('Ennyi auto valtozott: ' || darab);

end;
