begin
    open piros;
    loop
        fetch piros into x;
        exit when piros%notfound;
        insert into MasikPiros_Auto values (x.rsz, x.tipus, x.szin, x.kor, x.ar);
    end loop;
    close piros;
end;