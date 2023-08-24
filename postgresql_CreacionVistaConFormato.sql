-- View: public.v_clientes_saldos

-- DROP VIEW public.v_clientes_saldos;

CREATE OR REPLACE VIEW public.v_clientes_saldos
 AS
 WITH vendedores_por_cliente AS (
         SELECT factura.id_cliente,
            vendedor.nombre
           FROM factura
             LEFT JOIN vendedor ON factura.id_vendedor = vendedor.id_vendedor
          GROUP BY factura.id_cliente, vendedor.nombre
          ORDER BY factura.id_cliente
        ), vendedores_acumulados_por_cliente AS (
         SELECT vendedores_por_cliente.id_cliente,
            string_agg(vendedores_por_cliente.nombre::text, ', '::text) AS vendedor_acumulado
           FROM vendedores_por_cliente
          GROUP BY vendedores_por_cliente.id_cliente
        ), saldos_totales AS (
         SELECT factura.id_cliente AS cliente,
            sum(factura.total_factura) AS total
           FROM factura
          GROUP BY factura.id_cliente
          ORDER BY factura.id_cliente
        )
 SELECT cliente.id_cliente,
    cliente.nombre,
    cliente.ciudad,
    cliente.departamento,
    vendedores_acumulados_por_cliente.vendedor_acumulado,
    to_char(saldos_totales.total, '99G999G999D9S'::text) AS total
   FROM cliente
     LEFT JOIN saldos_totales ON cliente.id_cliente = saldos_totales.cliente
     LEFT JOIN vendedores_acumulados_por_cliente ON cliente.id_cliente = vendedores_acumulados_por_cliente.id_cliente
  ORDER BY cliente.nombre;

ALTER TABLE public.v_clientes_saldos
    OWNER TO postgres;

