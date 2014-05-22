function addFilho(id_pai){
	var filho = $("#f_0");
	var index = parseInt(filho.val());
	var template = '<div id="filho_0_'+index+'" class="col-md-12 form-group">\n\
						<label for="id" class="control-label">ID:</label>\n\
						<input type="text" name="treeMap[0]['+index+'][id]" id="id" class="form-control">\n\
						<label for="size" class="control-label">Tamanho:</label>\n\
						<input type="text" name="treeMap[0]['+index+'][size]" id="size" class="form-control" placeholder="Somente número. Min: 0.">\n\
						<label for="color" class="control-label">Cor:</label>\n\
						<input type="text" name="treeMap[0]['+index+'][color]" id="color" class="form-control" placeholder="Somente número. Ex: -100 ou 20">\n\
					</div>';
	$("#filho_"+id_pai).append(template);
	filho.val(index++);
}