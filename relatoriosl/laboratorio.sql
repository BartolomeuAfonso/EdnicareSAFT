SELECT  s.idServico, s.designacao AS produtos,SUM(f1.quantidade) AS quantidade, c.designacao AS categoria  from factura f INNER JOIN factura_itens f1 ON f.idfactura=f1.codigoFactura
INNER JOIN servicos s ON s.idServico=f1.codigoProduto
INNER JOIN categoriaservico c ON c.idcategoriaServico= s.codigoCategoria
WHERE s.codigoCategoria=2
AND DATE(f.dataFactura) BETWEEN '20210401' AND '20210431'  GROUP BY 1,4 ORDER BY s.idServico